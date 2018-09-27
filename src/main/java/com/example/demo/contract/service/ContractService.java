package com.example.demo.contract.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.activiti.domain.ProcessStatus;
import com.example.demo.activiti.domain.WorkflowDTO;
import com.example.demo.activiti.service.IWorkflowService;
import com.example.demo.contract.entity.Contract;
import com.example.demo.contract.entity.ContractDTO;
import com.example.demo.contract.repository.ContractRepository;

@Service
@Transactional
public class ContractService implements IContractService {
	
	@Autowired
	private ContractRepository contractRepository;
	
	@Autowired 
	private IWorkflowService workflowService;

	@Override
	public void save(Contract contract) {
		contractRepository.save(contract);
	}

	@Override
	public void deleteById(Long id) {
		contractRepository.deleteById(id);
	}

	@Override
	public void deleteAll(Long[] ids) {
		
        List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Contract> contractLists=(List<Contract>) contractRepository.findAllById(idLists);
		
		if(contractLists!=null) {
			contractRepository.deleteAll(contractLists);
		}

	}

	@Override
	public Page<Contract> findAll(Specification<Contract> spec, Pageable pageable) {
		return contractRepository.findAll(spec, pageable);
	}

	@Override
	public Contract findOne(Long id) {
		return contractRepository.findById(id).get();
	}
	
	
	
	
	/*----------------------------------------------流程业务--------------------------------------------*/
	/**
     * 开始流程
     *
     * @param userId 用户ID
     * @param pageable 分页条件
     * @return
     */
	@Override
	public void startWorkflow(String userId, Long contractId, Map<String, Object> variables) {
		//1.声明流程实例
		ProcessInstance processInstance = null;
		//2.获取创建好的请假实例
		Contract contract = contractRepository.findById(contractId).get();
		if(contract!=null){
			try {
				processInstance = workflowService.startWorkflow(userId, "contract", contract.getId().toString(), variables);
				contract.setProcessStatus(ProcessStatus.APPROVAL);
				contract.setProcessInstanceId(processInstance.getId());
				//leaveRepository.save(leave);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	 /**
     * 查询待办任务
     *
     * @param userId 用户ID
     * @param pageable 分页条件
     * @return
     */
	@Override
	public Page<ContractDTO> findTodoTasks(String userId, Pageable pageable) {
		List<ContractDTO> results = null;
		List<WorkflowDTO> workflowLists = workflowService.findTodoTasks(userId);
        // 根据流程的业务ID查询实体并关联
		if(null!=workflowLists) {
			results = new ArrayList<ContractDTO>();
			for (WorkflowDTO workflow : workflowLists) {
	        	Long businessKey = new Long(workflow.getBusinessKey());
	            if (workflow.getBusinessKey() == null) {
	                continue;
	            }
	            Contract contract = contractRepository.findById(businessKey).get();
	            if(contract!=null){
	            	ContractDTO contractDTO = new ContractDTO();
	            	BeanUtils.copyProperties(contract, contractDTO);
	            	BeanUtils.copyProperties(workflow, contractDTO);
	            	results.add(contractDTO);
	            }
	        }
		}
		return new PageImpl<ContractDTO> (results, pageable, null!=results?results.size():0);
	}
	
	
	/**
     * 签收流程任务
     *
     * @param taskId 任务ID
     * @param userId 签收人用户ID
     * @return
     */
	@Override
	public void claim(String taskId, String userId) {
		workflowService.claim(taskId, userId);
	}
	
	/**
     * 完成流程任务
     *
     * @param taskId 任务ID
     * @param variables 流程变量
     * @return
     */
	@Override
	public void complete(String taskId, Map<String, Object> variables) {
		workflowService.complete(taskId, variables);
	}

	

}
