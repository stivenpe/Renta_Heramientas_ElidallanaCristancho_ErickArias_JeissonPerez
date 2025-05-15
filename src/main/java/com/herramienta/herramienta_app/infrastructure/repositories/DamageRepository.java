package com.herramienta.herramienta_app.infrastructure.repositories;

import com.herramienta.herramienta_app.domain.entities.Damage;

public interface DamageRepository {
       void save(Damage Damage);
    Damage findById(Long idDamage);
    void update(Damage Damage);
    void delete(Long idDamage);

}
