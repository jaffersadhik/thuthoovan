package com.smsapi.dlt.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "dlt")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DLTModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dltid;
	
	@NotNull(message= "senderid not null")	
	@NotBlank(message= "senderid not blank")
	@Size(min = 1, max = 10, message= "senderid must be between 1 and 10 characters")
	private String senderid;

	@NotNull(message= "templateid not null")	
	@NotBlank(message= "templateid not blank")
	@Size(min = 1, max = 19, message= "templateid must be between 1 and 19 digits")
	@Digits(fraction = 0, integer = 19,message="templateid must be digit")
	private String templateid;
	
	@NotNull(message= "entityid not null")	
	@NotBlank(message= "entityid not blank")
	@Size(min = 1, max = 19, message= "entityid must be between 1 and 10 characters")
	@Digits(fraction = 0, integer = 19,message="entityid must be digit")
	private String entityid;
	
	private String smstemplate;
	
	public String getKey() {
		
		StringBuffer sb=new StringBuffer();
		
		return sb.append(senderid).append(":").append(entityid).append(":").append(templateid).toString();
	}
	
}
