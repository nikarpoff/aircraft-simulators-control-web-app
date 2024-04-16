package org.server.dal.repository;

import org.server.dal.model.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Integer> {

    Report findFirstByOrderByReportDateTimeDesc();

    List<Report> findReportsByReportDateTimeBetweenOrderByReportDateTime(LocalDateTime startDate, LocalDateTime endDate);

//    @Query(value = """
//    SELECT
//        joined.component_id,
//    	s.id,
//        json_agg(json_build_object(
//            'response_time', response_time,
//            'temperature', temperature,
//            'power', power,
//            'voltage', voltage
//        )) AS values
//    FROM
//        (
//    	SELECT
//    		ss.simulator_id,
//    		cs.id,
//    		r.report_date_time,
//    		cs.component_id,
//    		cs.response_time,
//    		cs.temperature,
//    		cs.power,
//    		cs.voltage FROM report r
//    	JOIN simulator_status ss ON ss.report_id=r.id
//    	JOIN component_status cs ON cs.simulator_status_id=ss.id
//    	WHERE r.report_date_time BETWEEN ?1 AND ?2
//    	ORDER BY r.report_date_time
//    	) AS joined
//    RIGHT JOIN simulator s ON joined.simulator_id=s.id
//    GROUP BY
//        component_id, s.id
//    ORDER BY
//        component_id
//    """, nativeQuery = true)
//    List<TestCkass> getComponentsStatistics(Date startDate, Date endDate);



}
