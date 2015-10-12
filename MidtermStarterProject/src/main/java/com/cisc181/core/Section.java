package com.cisc181.core;

import java.util.UUID;

public class Section {
	
	private UUID CourseID;
	private UUID SemesterID;
	private UUID SectionID;
	private UUID RoomID;
	
	public Section(UUID CourseID, UUID SemesterID,UUID SectionID){
		this.CourseID = CourseID;
		this.SemesterID = SemesterID;
		this.SectionID = SectionID;
	}
	
	public UUID getSectionID(){
		return SectionID;
	}
	

}
