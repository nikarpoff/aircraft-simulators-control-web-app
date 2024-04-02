package org.server.dal.repository;

import org.server.dal.model.Report;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Integer> {

    public Report findFirstByOrderByReportDateTimeDesc();

    public List<Report> findByReportDateTimeBetween(Date startDate, Date endDate);

}
