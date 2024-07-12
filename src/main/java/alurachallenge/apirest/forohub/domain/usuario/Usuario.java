package alurachallenge.apirest.forohub.domain.usuario;

import alurachallenge.apirest.forohub.domain.perfil.Perfil;
import alurachallenge.apirest.forohub.domain.respuesta.Respuesta;
import alurachallenge.apirest.forohub.domain.topico.Topico;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="usuario")
@Entity(name="Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correoElectronico;
    @JsonIgnore
    private String contrasena;
    @ManyToOne
    @JoinColumn(name="perfil_id")
    @JsonManagedReference
    private Perfil perfil;
    @OneToMany(mappedBy = "usuario")
    @JsonBackReference
    private List<Respuesta> respuesta;

    @OneToMany(mappedBy = "usuario")
    @JsonBackReference
    private List<Topico> topicos;

    public Usuario(DatosRegistroUsuario datosRegistradoUsuario, Perfil perfil) {
        this.nombre= datosRegistradoUsuario.nombre();
        this.correoElectronico = datosRegistradoUsuario.correoElectronico();
        this.contrasena = datosRegistradoUsuario.contrasena();
        this.perfil =perfil;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return contrasena;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
