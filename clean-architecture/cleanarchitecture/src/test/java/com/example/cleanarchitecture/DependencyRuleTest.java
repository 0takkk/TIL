package com.example.cleanarchitecture;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

public class DependencyRuleTest {

    @Test
    void domainLayerDoesNotDependOnApplicationLayer() {
        ArchRuleDefinition.noClasses()
                .that()
                .resideInAPackage("com.example.cleanarchitecture.account.domain..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage("com.example.cleanarchitecture.account.application..")
                .check(new ClassFileImporter()
                        .importPackages("com.example.cleanarchitecture.account.."));
    }
}
