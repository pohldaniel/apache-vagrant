package de.app.services;

import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import de.app.dao.TimesheetDao;
import de.app.entities.Timesheet;
import de.app.entities.enums.Category;
import de.app.utils.CalendarUtils;

@Service
public class TimesheetService {

	private TimesheetDao timesheetDao = TimesheetDao.getInstance();
	
	public boolean checkForOverlappingTimesheets(final Timesheet timesheet) {
		Iterable<Timesheet> timesheets = timesheetDao.findByPersonAndDate(timesheet.getPerson().getId(), timesheet.getStartTime());		
		return StreamSupport.stream(timesheets.spliterator(), false).filter(
			          ts -> {
				        	    if(ts.getId().equals(timesheet.getId())) 
				        	    	return false;
				        	    
				        	    if(ts.getCategory() == Category.VACATION || ts.getCategory() == Category.ILLNESS) {
				        	    	return true;
				        	    }
				        	    
				        	    if(ts.getEndTime().compareTo(timesheet.getStartTime()) <= 0) {
						        	return false;
						        }
				        	    
				        	    if(ts.getStartTime().compareTo(timesheet.getEndTime()) >= 0) {
						        	return false;
						        }
				        	    				        	   
				        	    return ts.getStartTime().compareTo(timesheet.getStartTime()) * timesheet.getStartTime().compareTo(ts.getEndTime()) >= 0 ||
				        	    ts.getStartTime().compareTo(timesheet.getEndTime()) * timesheet.getEndTime().compareTo(ts.getEndTime()) >= 0;
				            }
                )
                .collect(Collectors.toList()).size() > 0;
	}
	
	public boolean checkForOverlappingTimesheets(String id, Calendar from, Calendar until) {
		Iterable<Timesheet> timesheets = timesheetDao.findByPersonInRange(id, from, until);	
		return timesheets != null && timesheets.iterator().hasNext();
	}
	
	public void updateSummary(final Timesheet timesheet) {		
		Iterable<Timesheet> timesheets = timesheetDao.findByPersonAndDate(timesheet.getPerson().getId(), timesheet.getStartTime());	
		for(Timesheet ts : timesheets) {
			ts.setSummary(timesheetDao.getSummary(ts));
			timesheetDao.save(ts);
		}		
	}
	
	public int calculateBusinessDaysOfMonth(final Calendar month) {		
		Calendar start = CalendarUtils.GetDailyMin(month);
		start.set(Calendar.DAY_OF_MONTH, 1);		
		Calendar end = CalendarUtils.GetDailyMax(month);		
		end.set(Calendar.DAY_OF_MONTH, month.getActualMaximum(Calendar.DAY_OF_MONTH));

		int result = 0;
		for (Calendar date = start; date.before(end); date.add(Calendar.DATE, 1)){
			result = isBusinessDay(date) ? result + 1 : result;
		}	
		return result;
	}
	
	private boolean isBusinessDay(final Calendar calendar) {
		return calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY;	
	}
}
