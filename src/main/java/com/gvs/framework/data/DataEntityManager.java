package com.gvs.framework.data;

import com.gvs.framework.data.pojo.DataEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataEntityManager {
    private static final String POJO_TEMPLATE_FILE_PATH = "./src/main/resources/data/DataEntityTemplate.json";
    private static final String COST_CUTTER_PREFIX = "CostCutter";

    public String branchTown;

    private ObjectMapper mapper;

    DataEntityManager(){
        mapper = new ObjectMapper();
    }

    public DataEntity getNewBranchDetails(){
        DataEntity dataEntity = populateDataEntity(loadDataEntity(), COST_CUTTER_PREFIX);
        this.branchTown = dataEntity.getBranchDetails().getBranchTown();
        return dataEntity;
    }

    private DataEntity populateDataEntity(DataEntity dataEntity, String namingPrefix){

        dataEntity.getBranchDetails().setBranchTown(getRandomCity());
        //dataEntity.getBranchDetails().setBranchTown((getRandomNumber());
        return dataEntity;
    }

    private String getRandomNumber(){
        return RandomStringUtils.randomNumeric(11);
    }

    private String getRandomCity() {
        List<String> domainNameList = new ArrayList<String>();
        Random random = new Random();

        domainNameList.add("York");
        domainNameList.add("Oxford");
        domainNameList.add("London");
        domainNameList.add("New York");
        domainNameList.add("San Francisco");
        domainNameList.add("Birmingham");
        domainNameList.add("Miami");
        domainNameList.add("Montreal");
        domainNameList.add("Marseille");
        domainNameList.add("Madrid");
        domainNameList.add("Lisbon");
        domainNameList.add("Gibraltar");

        int index = random.nextInt(domainNameList.size())-1;
        index = 0;
        if(index < 0){
            index = 0;
        }
        return "@" + domainNameList.get(index);
    }


    private DataEntity loadDataEntity(){
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(POJO_TEMPLATE_FILE_PATH));
            String jsonString = new String(encoded, Charset.defaultCharset());
            return mapper.readValue(jsonString, DataEntity.class);
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean writeDataEntityToFile(DataEntity dataEntity, String filePath){
        try {
            String dataEntityAsString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataEntity);
            Files.write(Paths.get(filePath),
                    dataEntityAsString.getBytes(),
                    Files.exists(Paths.get(filePath)) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
