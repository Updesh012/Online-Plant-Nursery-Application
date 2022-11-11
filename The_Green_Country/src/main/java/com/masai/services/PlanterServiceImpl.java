package com.masai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.models.Planter;
import com.masai.models.PlanterDto;
import com.masai.repositories.PlanterRepo;

@Service
public class PlanterServiceImpl implements PlanterService{

	@Autowired
	private PlanterRepo pRepo;

	@Override
	public Planter addPlanterOnly(PlanterDto pDto) {
		
		Planter pObj = new Planter();
		pObj.setPlanterCapacity(pDto.getPlanterCapacity());
		pObj.setPlanterColor(pDto.getPlanterColor());
		pObj.setPlanterCost(pDto.getPlanterCost());
		pObj.setPlanterHeight(pDto.getPlanterHeight());
		pObj.setPlanterHoles(pDto.getPlanterHoles());
		pObj.setPlanterShape(pDto.getPlanterShape());
		pObj.setPlanterStock(pDto.getPlanterStock());
		
	    return pRepo.save(pObj);
		
		
	}
	
	
	
	
	
}
