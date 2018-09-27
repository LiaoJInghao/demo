package com.example.demo.contract.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.activiti.domain.ProcessStatus;
import com.example.demo.activiti.util.WorkflowVariable;
import com.example.demo.common.beans.BeanUtils;
import com.example.demo.common.web.ExtAjaxResponse;
import com.example.demo.common.web.ExtjsPageRequest;
import com.example.demo.common.web.SessionUtil;
import com.example.demo.contract.entity.Contract;
import com.example.demo.contract.entity.ContractDTO;
import com.example.demo.contract.entity.ContractQueryDTO;
import com.example.demo.contract.service.IContractService;

@RestController
@RequestMapping("/contract")
public class ContractController {
	
	@Autowired
	private IContractService contractService;
	
	/*save*/
	@PostMapping
	public ExtAjaxResponse saveOne(HttpSession session,@RequestBody Contract contract) {
		
		try {
			String userId = SessionUtil.getUserName(session);
			if(userId!=null) {
				contract.setUserId(userId);
				contract.setProcessStatus(ProcessStatus.NEW);
				contractService.save(contract);
			}
			return new ExtAjaxResponse(true,"保存成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"保存失败！");
		}
		
	}
	
	/*delete one*/
	@DeleteMapping(value="{id}")    
	public ExtAjaxResponse deleteById(@PathVariable("id") Long id) {
		
		try {
			Contract entity = contractService.findOne(id);
			if(entity!=null) {
				contractService.deleteById(id);
			}
			return new ExtAjaxResponse(true,"删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(false,"无该合同信息,删除失败！");
		}
		
	}
	
	/*delete rows*/
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) {
		
		try {
			if(ids!=null) {
				contractService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"批量删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(false,"批量删除失败！");
		}
		
	}
	
	/*update user*/
	@PutMapping(value="{id}")  
	public ExtAjaxResponse update(@PathVariable("id") Long Id,@RequestBody Contract dto) {
		
		try {
			Contract entity = contractService.findOne(Id);
			if(entity!=null) {
				BeanUtils.copyProperties(dto, entity);//使用自定义的BeanUtils
				contractService.save(entity);
			}
			return new ExtAjaxResponse(true,"更新成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"更新失败！");
		}
	}
	
	/*search one*/
	@GetMapping(value="{id}")  
	public Contract findById(@PathVariable("id") Long id){
		return contractService.findOne(id);
		
	}
	
	/*动态查询*/
	@GetMapping 
	public Page<Contract> getPage(HttpSession session,ContractQueryDTO contractQueryDTO,ExtjsPageRequest pageable) {
		
		Page<Contract> page;
		String userId = SessionUtil.getUserName(session);  //通过session查找userId
		if(userId!=null) {
			contractQueryDTO.setUserId(SessionUtil.getUserName(session));
			page = contractService.findAll(ContractQueryDTO.getWhereClause(contractQueryDTO), pageable.getPageable());
		}else {
			page = new PageImpl<Contract>(new ArrayList<Contract>(),pageable.getPageable(),0);
		}
		return page;
	}
	
	
	/*-------------------------------------流程引擎web层------------------------------------------*/
	/**
	 * 启动流程
	 * @param leaveId	请假信息Id
	 * @param session	通过会话获取登录用户(请假人)
	 * @return
	 */
	@RequestMapping(value = "/start")
    public @ResponseBody ExtAjaxResponse start(@RequestParam(name="id") Long contractId,HttpSession session) {
    	try {
    		String userId = SessionUtil.getUserName(session);
    		Map<String, Object> variables = new HashMap<String, Object>();
    		variables.put("deptLeader", "financeManager");
    		variables.put("manLeader", "hrManager");
    		variables.put("applyUserId", userId);
    		contractService.startWorkflow(userId,contractId, variables);
    		return new ExtAjaxResponse(true,"操作成功!");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"操作失败!");
	    }
    }
	
	
	/**
	 * 查询待处理流程任务
	 * @param pageable	分页对象
	 * @param session	通过会话获取登录用户(请假人)
	 * @return
	 */
	@RequestMapping(value = "/tasks")
    public @ResponseBody Page<ContractDTO> findTodoTasks(HttpSession session,ExtjsPageRequest pageable) {
		Page<ContractDTO> page = new PageImpl<ContractDTO>(new ArrayList<ContractDTO>(), pageable.getPageable(), 0);
    	try {
    		page = contractService.findTodoTasks(SessionUtil.getUserName(session), pageable.getPageable());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
    	
    	return page;
    }
	
	
	/**
     * 签收任务
     */
    @RequestMapping(value = "claim/{id}")
    public @ResponseBody ExtAjaxResponse claim(@PathVariable("id") String taskId, HttpSession session) {
    	try{
	    	contractService.claim(taskId, SessionUtil.getUserName(session));
	    	return new ExtAjaxResponse(true,"任务签收成功!");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"任务签收失败!");
	    }
    }
    
    
    /**
     * 完成任务
     * @param id
     * @return
     */
    @RequestMapping(value = "complete/{id}")
    public @ResponseBody ExtAjaxResponse complete(@PathVariable("id") String taskId, WorkflowVariable var) {
    	try{
    		Map<String, Object> variables = var.getVariableMap();
	    	contractService.complete(taskId, variables);
	    	return new ExtAjaxResponse(true,"审批成功!");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"审批失败!");
	    }
    }
	
}
