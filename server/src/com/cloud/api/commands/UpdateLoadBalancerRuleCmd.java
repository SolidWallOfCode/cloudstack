/**
 *  Copyright (C) 2010 Cloud.com, Inc.  All rights reserved.
 * 
 * This software is licensed under the GNU General Public License v3 or later.
 * 
 * It is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package com.cloud.api.commands;

import org.apache.log4j.Logger;

import com.cloud.api.ApiDBUtils;
import com.cloud.api.BaseAsyncCmd;
import com.cloud.api.BaseCmd.Manager;
import com.cloud.api.Implementation;
import com.cloud.api.Parameter;
import com.cloud.api.ResponseObject;
import com.cloud.api.response.LoadBalancerResponse;
import com.cloud.network.LoadBalancerVO;

@Implementation(method="updateLoadBalancerRule", manager=Manager.NetworkManager)
public class UpdateLoadBalancerRuleCmd extends BaseAsyncCmd {
    public static final Logger s_logger = Logger.getLogger(UpdateLoadBalancerRuleCmd.class.getName());
    private static final String s_name = "updateloadbalancerruleresponse";

    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////

    @Parameter(name="algorithm", type=CommandType.STRING)
    private String algorithm;

    @Parameter(name="description", type=CommandType.STRING)
    private String description;

    @Parameter(name="id", type=CommandType.LONG, required=true)
    private Long id;

    @Parameter(name="name", type=CommandType.STRING)
    private String loadBalancerName;

    @Parameter(name="privateport", type=CommandType.STRING)
    private String privatePort;

    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////

    public String getAlgorithm() {
        return algorithm;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getLoadBalancerName() {
        return loadBalancerName;
    }

    public String getPrivatePort() {
        return privatePort;
    }

    /////////////////////////////////////////////////////
    /////////////// API Implementation///////////////////
    /////////////////////////////////////////////////////

    @Override
    public String getName() {
        return s_name;
    }

	@Override
	public ResponseObject getResponse() {
	    LoadBalancerVO loadBalancer = (LoadBalancerVO)getResponseObject();

	    LoadBalancerResponse response = new LoadBalancerResponse();
        response.setAlgorithm(loadBalancer.getAlgorithm());
        response.setDescription(loadBalancer.getDescription());
        response.setId(loadBalancer.getId());
        response.setName(loadBalancer.getName());
        response.setPrivatePort(loadBalancer.getPrivatePort());
        response.setPublicIp(loadBalancer.getIpAddress());
        response.setPublicPort(loadBalancer.getPublicPort());
        response.setAccountName(loadBalancer.getAccountName());
        response.setDomainId(loadBalancer.getDomainId());
        response.setDomainName(ApiDBUtils.findDomainById(loadBalancer.getDomainId()).getName());

        response.setResponseName(getName());
        return response;
	}
}
