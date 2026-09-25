package com.skillissue.roastapp.config;

import com.skillissue.roastapp.model.Role;
import com.skillissue.roastapp.model.Subcategory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class RoleConfig {

    @Bean
    public Map<String , Role> roles() {
        // SDE
        double dsa = 0.25;
        double progLang = 0.25;
        double coreCs = 0.2;
        double systemDesign = 0.2;
        double debugging = 0.1;

        Subcategory dsaSub = new Subcategory("Data Structures", dsa);
        Subcategory progLangSub = new Subcategory("Programming Languages", progLang);
        Subcategory coreCsSub = new Subcategory("Core Computer Science", coreCs);
        Subcategory systemDesignSub = new Subcategory("System Design", systemDesign);
        Subcategory debuggingSub = new Subcategory("Debugging", debugging);

        Role sde = new Role("SDE" , List.of(dsaSub, progLangSub, coreCsSub, systemDesignSub, debuggingSub));

        //Data Analyst
        double sql = 0.25;
        double excel_sheets = 0.25;
        double python = 0.2;
        double statistics = 0.2;
        double powerBi_tableau =0.1;

        Subcategory sqlSub = new Subcategory("SQL", sql);
        Subcategory excelSheetsSub = new Subcategory("Excel/ Sheets", excel_sheets);
        Subcategory pythonSub = new Subcategory("Python", python);
        Subcategory statisticsSub = new Subcategory("Statistics", statistics);
        Subcategory powerBiTableauSub = new Subcategory("Power BI/ Tableau", powerBi_tableau);

        Role dataAnalyst = new Role("Data Analyst", List.of(sqlSub, excelSheetsSub, pythonSub, statisticsSub, powerBiTableauSub));

        //Full Stack Engineer
        double backend = 0.25;
        double frontend = 0.25;
        double databases = 0.2;
        double apisDesign = 0.2;
        double cloud_Deployment = 0.1;

        Subcategory backendSub = new Subcategory("Backend", backend);
        Subcategory frontendSub = new Subcategory("Frontend", frontend);
        Subcategory databasesSub = new Subcategory("Databases", databases);
        Subcategory apisDesignSub = new Subcategory("APIs Design", apisDesign);
        Subcategory cloudDeploymentSub = new Subcategory("Cloud Deployment", cloud_Deployment);

        Role fullStack = new Role("Full Stack Engineer", List.of(backendSub, frontendSub, databasesSub, apisDesignSub, cloudDeploymentSub));

        //AI Engineer
        double pythonAi = 0.25;
        double llm = 0.25;
        double vectorDb = 0.2;
        double ragFramework = 0.2;
        double promptEngineering = 0.1;

        Subcategory pythonAiSub = new Subcategory("Python AI", pythonAi);
        Subcategory llmSub = new Subcategory("LLM", llm);
        Subcategory vectorDbSub = new Subcategory("Vector DB", vectorDb);
        Subcategory ragFrameworkSub = new Subcategory("RAG Framework", ragFramework);
        Subcategory promptEngineeringSub = new Subcategory("Prompt Engineering", promptEngineering);

        Role aiEngineer = new Role("AI Engineer", List.of(pythonAiSub, llmSub, vectorDbSub, ragFrameworkSub, promptEngineeringSub));

        //ML Engineer
        double pythonMl = 0.25;
        double scikitLearn = 0.25;
        double pyTorch_tensorflow = 0.2;
        double pandas_numpy = 0.2;
        double mlOps = 0.1;

        Subcategory pythonMlSub = new Subcategory("Python ML", pythonMl);
        Subcategory scikitLearnSub = new Subcategory("Scikit Learn", scikitLearn);
        Subcategory pyTorchTensorflowSub = new Subcategory("PyTorch/ Tensorflow", pyTorch_tensorflow);
        Subcategory pandasNumpySub = new Subcategory("Pandas/ Numpy", pandas_numpy);
        Subcategory mlOpsSub = new Subcategory("MLOps", mlOps);

        Role mlEngineer = new Role("ML Engineer", List.of(pythonMlSub, scikitLearnSub, pyTorchTensorflowSub, pandasNumpySub, mlOpsSub));

        //Cloud Engineer
        double cloudPlatforms = 0.25;
        double networking = 0.25;
        double containers = 0.2;
        double ciCdAutomation = 0.2;
        double linux = 0.1;

        Subcategory cloudPlatformsSub = new Subcategory("Cloud Platforms", cloudPlatforms);
        Subcategory networkingSub = new Subcategory("Networking", networking);
        Subcategory containersSub = new Subcategory("Containers", containers);
        Subcategory ciCdAutomationSub = new Subcategory("CI/CD Automation", ciCdAutomation);
        Subcategory linuxSub = new Subcategory("Linux", linux);

        Role cloudEngineer = new Role("Cloud Engineer", List.of(cloudPlatformsSub, networkingSub, containersSub, ciCdAutomationSub, linuxSub));

        return Map.of("SDE", sde,
                "Data Analyst", dataAnalyst,
                "Full Stack Engineer", fullStack,
                "AI Engineer", aiEngineer,
                "ML Engineer", mlEngineer,
                "Cloud Engineer", cloudEngineer);
    }
}