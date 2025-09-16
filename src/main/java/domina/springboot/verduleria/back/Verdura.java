package domina.springboot.verduleria.back;

import org.springframework.data.annotation.Id;

public record Verdura(@Id long id, String nombre, Double precio, boolean troceable) {

}
