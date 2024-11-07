package de.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.app.dao.TimesheetDao;
import de.app.rest.TimesheetRestController;
import de.app.services.TimesheetService;

@RestController
@RequestMapping("/timera")
public class TimesheetRestController {
	private Logger LOG = LoggerFactory.getLogger(TimesheetRestController.class);
	
	private TimesheetDao timesheetDao = TimesheetDao.getInstance();

	@Autowired
	protected TimesheetService timesheetService;
	
	@RequestMapping(value = "/timesheet/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllTopicAreas() {
    	LOG.info("/timesheets/getAll");
        return ResponseEntity.status(HttpStatus.OK).body(timesheetDao.findAll());
    }
}
