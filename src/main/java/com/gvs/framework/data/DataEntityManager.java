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
    private static final String SOLE_TRADER_PREFIX = "SoleTrader";
    private static final String LIMITED_COMPANY_PREFIX = "LimitedCompany";
    private static final String LIMITED_LIABILITY_PARTNERSHIP_PREFIX = "LimitedLiabilityPartnership";
    private static final String PUBLIC_LIMITED_COMPANY_PREFIX = "PublicLimitedCompany";
    private static final String NON_UK_ORG_PREFIX = "NonUkOrg";
    private static final String CHARITY_PREFIX = "Charity";
    private static final String BUSINESS_PREFIX = "Business";
    private static final String CHILD_ORG_PREFIX = "ChildOrgBusiness";

    private static final String INDIVIDUAL_PREFIX = "Individual";

    private static final String INTERMEDIARY_SUFFIX = "INT";
    private static final String ADMIN_SUFFIX = "ADMIN";
    private static final String TEAM_MEMBER_SUFFIX = "TM";
    public String companyName;
    public String adminEmailID;
    private ObjectMapper mapper;

    DataEntityManager(){
         mapper = new ObjectMapper();
    }

    public DataEntity getNewSoleTraderEntity(){
        DataEntity dataEntity = populateDataEntity(loadDataEntity(),SOLE_TRADER_PREFIX);
        dataEntity.getAddress().setPostCode("cf105fy");
        this.companyName = dataEntity.getBusinessDetails().getCompanyName();
        this.adminEmailID= dataEntity.getAdminOrDirector().getEmailId();
        return dataEntity;
    }
    public DataEntity getNewCharityEntity(){
        DataEntity dataEntity =populateDataEntity(loadDataEntity(),CHARITY_PREFIX);
        this.adminEmailID= dataEntity.getAdminOrDirector().getEmailId();
        return dataEntity;
       // return populateDataEntity(loadDataEntity(),CHARITY_PREFIX);
    }
    public DataEntity getNewLimitedCompanyEntity(){
        DataEntity dataEntity=populateDataEntity(loadDataEntity(),LIMITED_COMPANY_PREFIX);
        this.adminEmailID= dataEntity.getAdminOrDirector().getEmailId();
        return dataEntity;
         }
    public DataEntity getNewLimitedLiabilityPartnershipEntity(){
        return populateDataEntity(loadDataEntity(),LIMITED_LIABILITY_PARTNERSHIP_PREFIX);
    }
    public DataEntity getNewPublicLimitedCompanyEntity(){
        return populateDataEntity(loadDataEntity(),PUBLIC_LIMITED_COMPANY_PREFIX);
    }
    public DataEntity getNewNonUkOrgEntity(){
        DataEntity dataEntity = populateDataEntity(loadDataEntity(),SOLE_TRADER_PREFIX);
        //dataEntity.getAddress().setCountry("Bulgaria");


        dataEntity = populateDataEntity(loadDataEntity(),NON_UK_ORG_PREFIX);
        dataEntity.getAddress().setCountry("Bulgaria");
        return dataEntity;
    }
//570
    /**
     * Creates TestData for Individual business type
     */
    public DataEntity getNewIndividualEntity(){
        DataEntity  dataEntity = populateDataEntity(loadDataEntity(),INDIVIDUAL_PREFIX);
        dataEntity.getAddress().setCountry("Bulgaria");
        return dataEntity;
    }



    private DataEntity populateDataEntity(DataEntity dataEntity, String namingPrefix){
        String intermediaryLastName = RandomStringUtils.randomAlphanumeric(4).toLowerCase();
        String adminLastName = RandomStringUtils.randomAlphanumeric(4).toLowerCase();
        String teamMemberLastName = RandomStringUtils.randomAlphanumeric(4).toLowerCase();

        dataEntity.getIntermediary().setFirstName(namingPrefix + INTERMEDIARY_SUFFIX);
        dataEntity.getIntermediary().setSecondName(intermediaryLastName);
        dataEntity.getIntermediary().setFullName(namingPrefix + intermediaryLastName);
        dataEntity.getIntermediary().setContactNumber(getRandomPhoneNumber());
        dataEntity.getIntermediary().setEmailId(intermediaryLastName + getRandomDomainName());

        dataEntity.getAddress().setBuildingNumber(RandomStringUtils.randomNumeric(2));
        dataEntity.getChildOrgAddress().setBuildingNumber(RandomStringUtils.randomNumeric(2));

        dataEntity.getBusinessDetails().setCompanyName(RandomStringUtils.randomNumeric(5));
        dataEntity.getBusinessDetails().setBusinessContactNumber(getRandomPhoneNumber());
        dataEntity.getBusinessDetails().setBusinessName(BUSINESS_PREFIX+
                RandomStringUtils.randomAlphanumeric(2));
        dataEntity.getBusinessDetails().setBusinessEmailId(BUSINESS_PREFIX +
                RandomStringUtils.randomAlphanumeric(2) + getRandomDomainName());

        dataEntity.getAdminOrDirector().setFirstName(namingPrefix + ADMIN_SUFFIX);
        dataEntity.getAdminOrDirector().setSecondName(adminLastName);
        dataEntity.getAdminOrDirector().setFullName(namingPrefix + adminLastName);
        dataEntity.getAdminOrDirector().setContactNumber(getRandomPhoneNumber());
        dataEntity.getAdminOrDirector().setEmailId(adminLastName + getRandomDomainName());

        dataEntity.getAdminOrDirector().setFirstName(namingPrefix + TEAM_MEMBER_SUFFIX);
        dataEntity.getAdminOrDirector().setSecondName(teamMemberLastName);
        dataEntity.getAdminOrDirector().setFullName(namingPrefix + teamMemberLastName);
        dataEntity.getAdminOrDirector().setContactNumber(getRandomPhoneNumber());
        dataEntity.getAdminOrDirector().setEmailId(teamMemberLastName + getRandomDomainName());

        dataEntity.getChildOrgBusinessDetails().setBusinessContactNumber(getRandomPhoneNumber());
        dataEntity.getChildOrgBusinessDetails().setBusinessName(CHILD_ORG_PREFIX +
                RandomStringUtils.randomAlphanumeric(2));
        dataEntity.getChildOrgBusinessDetails().setBusinessEmailId(CHILD_ORG_PREFIX+
                RandomStringUtils.randomAlphanumeric(2) + getRandomDomainName());
        return dataEntity;
    }

    private String getRandomPhoneNumber(){
        return RandomStringUtils.randomNumeric(11);
    }

    private String getRandomDomainName() {
        List<String> domainNameList = new ArrayList<String>();
        Random random = new Random();

        domainNameList.add("abyssmail.com");
        domainNameList.add("getnada.com");
        domainNameList.add("boximail.com");
        domainNameList.add("clrmail.com");
        domainNameList.add("dropjar.com");
        domainNameList.add("getairmail.com");
        domainNameList.add("givmail.com");
        domainNameList.add("inboxbear.com");
        domainNameList.add("robot-mail.com");
        domainNameList.add("tafmail.com");
        domainNameList.add("vomoto.com");
        domainNameList.add("zetmail.com");

        int index = random.nextInt(domainNameList.size())-1;
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
