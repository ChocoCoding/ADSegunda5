package org.example.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "libro")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLibro")
    private int idLibro;

    @NonNull
    private String codigo;
    @NonNull
    private String titulo;
    @NonNull
    private String autor;
    @NonNull
    private int anho;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Alquiler> alquileresLibro;

    public void addAlquiler(Alquiler alquiler){
        this.alquileresLibro.add(alquiler);
    }

}
