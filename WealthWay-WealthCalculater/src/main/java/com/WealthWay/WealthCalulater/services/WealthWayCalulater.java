package com.WealthWay.WealthCalulater.services;

import com.WealthWay.WealthCalulater.model.LumsumDto;
import com.WealthWay.WealthCalulater.model.SipDto;
import com.WealthWay.WealthCalulater.model.WealthProjectedDto;

public interface WealthWayCalulater {
	
	public  WealthProjectedDto lumpsumWeatlh(LumsumDto dto);
	public  WealthProjectedDto SipWealth(SipDto dto);

}
