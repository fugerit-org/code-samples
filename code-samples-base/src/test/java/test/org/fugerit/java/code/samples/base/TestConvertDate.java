package test.org.fugerit.java.code.samples.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
class TestConvertDate {

      @Test
    void testConvert() throws JsonProcessingException {
        String json = "{\"birthDate\":\"1969-06-01T01:00:00\"}";
        ObjectMapper mapper = new ObjectMapper();
        TestModel testModel = mapper.readValue( json, TestModel.class );
        LocalDate birthDate = testModel.getBirthDate();
        log.info( "birthDate: {}", birthDate );
    }

}

@Slf4j
class TestModel {

    public void setBirthDate( String v ) {
        try {
            if ( v != null && v.length() >= 10 ) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dv = v.substring( 0, 10 );
                log.info( "dv: {}", dv );
                this.birthDateD = LocalDate.parse(dv, formatter);
            }
        } catch (Exception e) {
            throw new RuntimeException( String.format( "Exception formatting birthdate %s", v ), e);
        }
    }

    public LocalDate getBirthDate() {
        return this.birthDateD;
    }

    private LocalDate birthDateD;
    
}
