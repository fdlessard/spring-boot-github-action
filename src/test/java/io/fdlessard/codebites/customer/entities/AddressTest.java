package io.fdlessard.codebites.customer.entities;

import com.fasterxml.jackson.databind.JsonNode;
import io.fdlessard.codebites.customer.TestUtils;
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

import static io.fdlessard.codebites.customer.TestUtils.buildAddress;
import static io.fdlessard.codebites.customer.TestUtils.buildCustomer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AddressTest {

    private static final ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = vf.getValidator();

    @Test
    void addressMarshallingToJson() {
        var address = buildAddress();
        var node = TestUtils.getObjectMapper().convertValue(address, JsonNode.class);
        assertAddressNode(address, node);
    }

    @Test
    void addressUnMarshallingFromJson() throws IOException {

        var address = TestUtils
                .readFileIntoPojo("/Address.json", Address.class);
        assertAddress(address);
    }

    @Test
    void validateNumberNotBlank() {

        var address = buildAddress();
        address.setNumber(null);

        var violationSet = validator.validate(address, Default.class);

        long violationsCount = TestUtils.getViolationsCount(violationSet,
                "number cannot be blank", "number");
        assertEquals(1, violationsCount);

        address.setNumber(RandomStringUtils.random(3, StringUtils.SPACE));

        violationSet = validator.validate(address, Default.class);
        violationsCount = TestUtils.getViolationsCount(violationSet,
                "number cannot be blank", "number");

        assertEquals(1, violationsCount);
    }

    @Test
    void validateStreetNotBlank() {

        var address = buildAddress();
        address.setStreet(null);

        var violationSet = validator.validate(address, Default.class);

        long violationsCount = TestUtils.getViolationsCount(violationSet,
                "street cannot be blank", "street");
        assertEquals(1, violationsCount);

        address.setStreet(RandomStringUtils.random(3, StringUtils.SPACE));

        violationSet = validator.validate(address, Default.class);
        violationsCount = TestUtils.getViolationsCount(violationSet,
                "street cannot be blank", "street");

        assertEquals(1, violationsCount);
    }

    @Test
    void validateStreetMinSize() {

        var address = buildAddress();
        address.setStreet("a");

        var violationSet = validator.validate(address, Default.class);

        var violationsCount = TestUtils.getViolationsCount(violationSet,
                "street must have more thant 2 characters", "street");
        assertEquals(1, violationsCount);
    }

    @Test
    void validateCityNotBlank() {

        var address = buildAddress();
        address.setCity(null);

        var violationSet = validator.validate(address, Default.class);

        long violationsCount = TestUtils.getViolationsCount(violationSet,
                "city cannot be blank", "city");
        assertEquals(1, violationsCount);

        address.setCity(RandomStringUtils.random(3, StringUtils.SPACE));

        violationSet = validator.validate(address, Default.class);
        violationsCount = TestUtils.getViolationsCount(violationSet,
                "city cannot be blank", "city");

        assertEquals(1, violationsCount);
    }

    @Test
    void validateCityMinSize() {

        var address = buildAddress();
        address.setCity("a");

        var violationSet = validator.validate(address, Default.class);

        var violationsCount = TestUtils.getViolationsCount(violationSet,
                "city must have more thant 2 characters", "city");
        assertEquals(1, violationsCount);
    }

    @Test
    void equalsContract() {

        var c = buildCustomer();
        c.setId(1L);

        EqualsVerifier.forClass(Address.class)
                .withRedefinedSuperclass()
                .suppress(Warning.STRICT_INHERITANCE)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .withPrefabValues(Customer.class, buildCustomer(), c)
                .verify();

        var address1 = buildAddress();
        var address2 = new Address();
        address2.setId(0L);
        address2.setVersion(0);
        address2.setNumber("100");
        address2.setStreet("The Street");
        address2.setCity("A City");
        address2.setPostalCode("X0X 0X0");
        address2.setProvince("QC");
        address2.setCountry("CA");


        assertEquals(address1, address2);
        assertEquals(address1.toString(), address2.toString());
    }

    private void assertAddress(Address address) {
        assertEquals(0, address.getId());
        assertEquals("100", address.getNumber());
        assertEquals("The Street", address.getStreet());
        assertEquals("A City", address.getCity());
        assertEquals("X0X 0X0", address.getPostalCode());
        assertEquals("QC", address.getProvince());
        assertEquals("CA", address.getCountry());
    }

    public static void assertAddressNode(Address address, JsonNode node) {

        assertEquals(address.getId(), node.get("id").asLong());
        assertFalse(node.has("version"));
        assertEquals(address.getNumber(), node.get("number").asText());
        assertEquals(address.getStreet(), node.get("street").asText());
        assertEquals(address.getCity(), node.get("city").asText());
        assertEquals(address.getPostalCode(), node.get("postalCode").asText());
        assertEquals(address.getProvince(), node.get("province").asText());
        assertEquals(address.getCountry(), node.get("country").asText());
    }

}