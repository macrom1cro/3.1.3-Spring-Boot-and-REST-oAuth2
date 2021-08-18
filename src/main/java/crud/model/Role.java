package crud.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

// Этот класс реализует интерфейс GrantedAuthority, в котором необходимо переопределить только один метод getAuthority() (возвращает имя роли).
// Имя роли должно соответствовать шаблону: «ROLE_ИМЯ», например, ROLE_USER.
@Entity
@Table(name = "roles")
@Data//ломбок аннотация: генерирует геттеры, сеттеры, иквалс, хеш код методы
@NoArgsConstructor//ломбок аннотация: конструктор без аргуметов
public class Role implements GrantedAuthority {

    @Id
    private Long id;

    @Column
    private String role;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(role);

        return sb.delete(0,5).toString();
    }

//    @Override
//    public String toString() {
//        return role;
//    }

    @Override
    public String getAuthority() {
        return role;
    }

}
