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

package com.cloud.network;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import com.cloud.domain.PartOf;
import com.cloud.user.OwnedBy;

@Entity
@Table(name=("security_group"))
@SecondaryTable(name="account",
        pkJoinColumns={@PrimaryKeyJoinColumn(name="account_id", referencedColumnName="id")})
public class SecurityGroupVO implements PartOf, OwnedBy {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="domain_id")
    private long domainId;

    @Column(name="account_id")
    private long accountId;
    
    @Column(name="account_name", table="account", insertable=false, updatable=false)
    private String accountName = null;

    public SecurityGroupVO() {}

    public SecurityGroupVO(String name, String description, long domainId, long accountId) {
        this.name = name;
        this.description = description;
        this.domainId = domainId;
        this.accountId = accountId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getDomainId() {
        return domainId;
    }

    public long getAccountId() {
        return accountId;
    }
    
    public String getAccountName() {
        return accountName;
    }
}
