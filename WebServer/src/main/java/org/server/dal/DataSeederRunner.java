package org.server.dal;

import lombok.extern.slf4j.Slf4j;
import org.server.dal.exception.DatabaseException;
import org.server.dal.service.DataSeederService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataSeederRunner implements ApplicationRunner {

    private final DataSeederService dataSeederService;

    public DataSeederRunner(DataSeederService dataSeederService) {
        this.dataSeederService = dataSeederService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            dataSeederService.initData();
        } catch (DatabaseException exception) {
            log.error(exception.getMessage());
        }
    }
}
