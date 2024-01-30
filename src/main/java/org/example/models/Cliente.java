package org.example.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cliente")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private int idCliente;

    @NonNull
    @Column(name = "DNI", columnDefinition = "char")
    private String dni;
    @NonNull
    private String nombre;
    @NonNull
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Alquiler> alquileresCliente;

    public void addAlquiler(Alquiler alquiler){
        this.alquileresCliente.add(alquiler);
    }

}
