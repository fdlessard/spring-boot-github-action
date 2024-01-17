package io.fdlessard.codebites.customer.entities;

import com.fasterxml.jackson.databind.JsonNode;
import io.fdlessard.codebites.TestUtils;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.groups.Default;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;

import static io.fdlessard.codebites.TestUtils.buildAddress;
import static io.fdlessard.codebites.TestUtils.buildCustomer;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private static final ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = vf.getValidator();

    @Test
    void customerMarshallingToJson() {
        var customer = buildCustomer();
        var node = TestUtils.getObjectMapper().convertValue(customer, JsonNode.class);
        assertCustomerNode(customer, node);
    }

    @Test
    void customerUnMarshallingFromJson() throws IOException {

        var customer = TestUtils
                .readFileIntoPojo("/Customer.json", Customer.class);
        assertCustomer(customer);
    }

    @Test
    void validateFirstNameNotBlank() {

        var customer = buildCustomer();
        customer.setFirstName(null);

        var violationSet = validator.validate(customer, Default.class);

        long violationsCount = TestUtils.getViolationsCount(violationSet,
                "firstName name cannot be blank", "firstName");
        assertEquals(1, violationsCount);

        customer.setFirstName(RandomStringUtils.random(3, StringUtils.SPACE));

        violationSet = validator.validate(customer, Default.class);
        violationsCount = TestUtils.getViolationsCount(violationSet,
                "firstName name cannot be blank", "firstName");

        assertEquals(1, violationsCount);
    }

    @Test
    void validateFirstNameMinSize() {

        var customer = buildCustomer();
        customer.setFirstName("a");

        var violationSet = validator.validate(customer, Default.class);

        var violationsCount = TestUtils.getViolationsCount(violationSet,
                "firstName must have more than 2 characters", "firstName");
        assertEquals(1, violationsCount);
    }

    @Test
    void validateLastNameNotBlank() {

        var customer = buildCustomer();
        customer.setLastName(null);

        var violationSet = validator.validate(customer, Default.class);

        long violationsCount = TestUtils.getViolationsCount(violationSet,
                "lastName name cannot be blank", "lastName");
        assertEquals(1, violationsCount);

        customer.setFirstName(RandomStringUtils.random(3, StringUtils.SPACE));

        violationSet = validator.validate(customer, Default.class);
        violationsCount = TestUtils.getViolationsCount(violationSet,
                "lastName name cannot be blank", "lastName");

        assertEquals(1, violationsCount);
    }

    @Test
    void validateLastNameMinSize() {

        var customer = buildCustomer();
        customer.setLastName("a");

        var violationSet = validator.validate(customer, Default.class);

        var violationsCount = TestUtils.getViolationsCount(violationSet,
                "lastName must have more than 2 characters", "lastName");
        assertEquals(1, violationsCount);
    }

    @Test
    void validateCompanyNotBlank() {

        var customer = buildCustomer();
        customer.setCompany(null);

        var violationSet = validator.validate(customer, Default.class);

        long violationsCount = TestUtils.getViolationsCount(violationSet,
                "company name cannot be blank", "company");
        assertEquals(1, violationsCount);

        customer.setFirstName(RandomStringUtils.random(3, StringUtils.SPACE));

        violationSet = validator.validate(customer, Default.class);
        violationsCount = TestUtils.getViolationsCount(violationSet,
                "company name cannot be blank", "company");

        assertEquals(1, violationsCount);
    }

    @Test
    void validateCompanyMinSize() {

        var customer = buildCustomer();
        customer.setCompany("a");

        var violationSet = validator.validate(customer, Default.class);

        var violationsCount = TestUtils.getViolationsCount(violationSet,
                "company must have more than 2 characters", "company");
        assertEquals(1, violationsCount);
    }

    @Test
    void equalsContract() {

        var a = buildAddress();
        a.setId(1L);

        EqualsVerifier.forClass(Customer.class)
                .withRedefinedSuperclass()
                .suppress(Warning.STRICT_INHERITANCE)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .withPrefabValues(Address.class, buildAddress(), a)
                .verify();

        var customer1 = buildCustomer();
        var customer2 = new Customer();
        customer2.setId(0L);
        customer2.setVersion(0);
        customer2.setFirstName("firstname");
        customer2.setLastName("lastname");
        customer2.setCompany("company");

        assertEquals(customer1, customer2);
        assertEquals(customer1.toString(), customer2.toString());
    }

    private void assertCustomer(Customer customer) {
        assertEquals(0, customer.getId());
        assertEquals("firstname", customer.getFirstName());
        assertEquals("lastname", customer.getLastName());
        assertEquals("company", customer.getCompany());
    }

    public static void assertCustomerNode(Customer customer, JsonNode node) {

        assertEquals(customer.getId(), node.get("id").asLong());
        assertFalse(node.has("version"));
        assertEquals(customer.getFirstName(), node.get("firstName").asText());
        assertEquals(customer.getLastName(), node.get("lastName").asText());
        assertEquals(customer.getCompany(), node.get("company").asText());

    }

}