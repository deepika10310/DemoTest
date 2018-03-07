package com.edu.yati.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Challenge implements Serializable {
	private static final long serialVersionUID = -872023384718294379L;
	@Id
    @JsonProperty
    private String id;
	private long startTime;
	private long endTime;
	private String standard;
	private String name;
	long duration;
	boolean publish;
	//private Problem[] problem;
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	/*public Problem[] getProblem() {
		return problem;
	}
	public void setProblem(Problem[] problem) {
		this.problem = problem;
	}*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isPublish() {
		return publish;
	}
	public void setPublish(boolean publish) {
		this.publish = publish;
	}
	@Override
	public String toString() {
		return "Challenge [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", standard=" + standard
				+ ", name=" + name + ", duration=" + duration + ", publish=" + publish + "]";
	}
	
	
}
