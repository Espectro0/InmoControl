# Transacciones por DAO — DAOFactory

Basado en el Modelo de Dominio (líneas de vida, responsabilidades y caracterización de cada objeto).

---

## Resumen de DAOs necesarios

| DAO | Línea de vida | Transacciones |
|---|---|---|
| `PaisDAO` | 1 | findById, findAll |
| `EstudoDAO` | 1 | findById, findAll |
| `TipoDocumentoDAO` | 1 | findById, findAll |
| `TipoPropiedadDAO` | 1 | findById, findAll |
| `TipoParticipanteDAO` | 1 | findById, findAll |
| `AreaReferenciaDAO` | 1 | findById, findAll, save, update, delete |
| `TipoAplicacionDAO` | 1 | findById, findAll |
| `DepartamentoDAO` | 2 | findById, findAll |
| `ClausulaContratoDAO` | 2 | findById, findAll, save, update, delete |
| `CiudadDAO` | 3 | findById, findAll |
| `PersonaDAO` | 4 | findById, findAll, save, update |
| `ParticipanteContratoDAO` | 5 | findAllByContrato, save, delete |
| `ParametroDAO` | 5 | findById, findAll |
| `ParametroClausulaContratoDAO` | 6 | findAllByClausulaPorContrato, save, update, delete |
| `PropiedadDAO` | 6 | findById, findAll, save, update |
| `ContratoDAO` | 7 | findById, findAll, save, update (modificar, suspender, activar) |
| `ClausulaPorContratoDAO` | 7 | findAllByContrato, save, update, delete |

---

## Detalle por DAO

### Catálogos de solo lectura (Línea de vida 1)
Los catálogos puros solo exponen consultas. No tienen responsabilidades de escritura en el modelo de dominio.

```
PaisDAO
  - findById(UUID id) → Pais
  - findAll() → List<Pais>

EstratoDAO
  - findById(UUID id) → Estrato
  - findAll() → List<Estrato>

TipoDocumentoDAO
  - findById(UUID id) → TipoDocumento
  - findAll() → List<TipoDocumento>

TipoPropiedadDAO
  - findById(UUID id) → TipoPropiedad
  - findAll() → List<TipoPropiedad>

TipoParticipanteDAO
  - findById(UUID id) → TipoParticipante
  - findAll() → List<TipoParticipante>

TipoAplicacionDAO
  - findById(UUID id) → TipoAplicacion
  - findAll() → List<TipoAplicacion>
```

---

### AreaReferenciaDAO (Línea de vida 1, CRUD completo)
Tiene responsabilidades de Agregar, Modificar y Eliminar según el modelo.

```
AreaReferenciaDAO
  - findById(UUID id)   → AreaReferencia
  - findAll()           → List<AreaReferencia>            // soporta filtro por nombre
  - save(AreaReferencia) → AreaReferencia
  - update(AreaReferencia) → AreaReferencia
  - delete(UUID id)     → void
```

---

### DepartamentoDAO (Línea de vida 2)
Solo consultas. El modelo no registra responsabilidades de escritura.

```
DepartamentoDAO
  - findById(UUID id)            → Departamento
  - findAll()                    → List<Departamento>     // filtro opcional por pais_id
  - findAllByPais(UUID paisId)   → List<Departamento>
```

---

### CiudadDAO (Línea de vida 3)
Solo consultas. El modelo no registra responsabilidades de escritura.

```
CiudadDAO
  - findById(UUID id)                      → Ciudad
  - findAll()                              → List<Ciudad>  // filtros por nombre, departamento
  - findAllByDepartamento(UUID depId)      → List<Ciudad>
```

---

### ClausulaContratoDAO (Línea de vida 2, CRUD completo)
Registrar, Consultar, Modificar y Eliminar según el modelo.

```
ClausulaContratoDAO
  - findById(UUID id)              → ClausulaContrato
  - findAll(filtros opcionales)    → List<ClausulaContrato>
      // filtros: areaReferencia_id, tipoAplicacion_id, titulo
  - save(ClausulaContrato)         → ClausulaContrato
  - update(ClausulaContrato)       → ClausulaContrato
  - delete(UUID id)                → void
```

---

### PersonaDAO (Línea de vida 4)
El modelo define Consultar y (implícitamente) Registrar para poder ser participante.

```
PersonaDAO
  - findById(UUID id)                          → Persona
  - findAll()                                   → List<Persona>
  - findByNumeroIdentificacion(String numero)   → Persona
  - save(Persona)                               → Persona
  - update(Persona)                             → Persona
```

---

### ParametroDAO (Línea de vida 5)
Solo consultas según el modelo. Los parámetros se crean a nivel de sistema.

```
ParametroDAO
  - findById(UUID id)    → Parametro
  - findAll()            → List<Parametro>
```

---

### ParticipanteContratoDAO (Línea de vida 5)
Se gestiona siempre en contexto de un contrato específico.

```
ParticipanteContratoDAO
  - findById(UUID id)                          → ParticipanteContrato
  - findAllByContrato(UUID contratoId)         → List<ParticipanteContrato>
  - save(ParticipanteContrato)                 → ParticipanteContrato
  - delete(UUID id)                            → void
```

---

### PropiedadDAO (Línea de vida 6)
El modelo implica Registrar y Consultar para ser objeto de un contrato.

```
PropiedadDAO
  - findById(UUID id)                 → Propiedad
  - findAll(filtros opcionales)       → List<Propiedad>
      // filtros: ciudad_id, tipoPropiedad_id, estrato_id
  - save(Propiedad)                   → Propiedad
  - update(Propiedad)                 → Propiedad
```

---

### ParametroClausulaContratoDAO (Línea de vida 6)
Se gestiona siempre en contexto de una ClausulaPorContrato.

```
ParametroClausulaContratoDAO
  - findById(UUID id)                                        → ParametroClausulaContrato
  - findAllByClausulaPorContrato(UUID clausulaPorContratoId) → List<ParametroClausulaContrato>
  - save(ParametroClausulaContrato)                          → ParametroClausulaContrato
  - update(ParametroClausulaContrato)                        → ParametroClausulaContrato
  - delete(UUID id)                                          → void
```

---

### ContratoDAO (Línea de vida 7, CRUD + estados)
Tiene responsabilidades de Registrar, Consultar, Modificar, Suspender y Activar.

```
ContratoDAO
  - findById(UUID id)                     → Contrato
  - findAll(filtros opcionales)           → List<Contrato>
      // filtros: propiedad_id, codigoContrato, fechaInicio, fechaFin, esActivo
  - save(Contrato)                        → Contrato
  - update(Contrato)                      → Contrato     // Modificar
  - updateEstado(UUID id, boolean activo) → void         // Suspender / Activar
```

---

### ClausulaPorContratoDAO (Línea de vida 7)
Agregar, Consultar, Modificar orden y Eliminar cláusulas de un contrato.

```
ClausulaPorContratoDAO
  - findById(UUID id)                         → ClausulaPorContrato
  - findAllByContrato(UUID contratoId)        → List<ClausulaPorContrato>
  - save(ClausulaPorContrato)                 → ClausulaPorContrato
  - update(ClausulaPorContrato)               → ClausulaPorContrato   // actualiza numeroClausula
  - delete(UUID id)                           → void
```

---

## Esquema del DAOFactory

```java
public class DAOFactory {

    // Catálogos de solo lectura
    public PaisDAO                      getPaisDAO()                      { ... }
    public EstratoDAO                   getEstratoDAO()                   { ... }
    public TipoDocumentoDAO             getTipoDocumentoDAO()             { ... }
    public TipoPropiedadDAO             getTipoPropiedadDAO()             { ... }
    public TipoParticipanteDAO          getTipoParticipanteDAO()          { ... }
    public TipoAplicacionDAO            getTipoAplicacionDAO()            { ... }
    public ParametroDAO                 getParametroDAO()                 { ... }

    // Catálogos con escritura
    public AreaReferenciaDAO            getAreaReferenciaDAO()            { ... }
    public DepartamentoDAO              getDepartamentoDAO()              { ... }
    public CiudadDAO                    getCiudadDAO()                    { ... }
    public ClausulaContratoDAO          getClausulaContratoDAO()          { ... }

    // Entidades principales
    public PersonaDAO                   getPersonaDAO()                   { ... }
    public PropiedadDAO                 getPropiedadDAO()                 { ... }
    public ContratoDAO                  getContratoDAO()                  { ... }

    // Entidades de relación
    public ParticipanteContratoDAO      getParticipanteContratoDAO()      { ... }
    public ClausulaPorContratoDAO       getClausulaPorContratoDAO()       { ... }
    public ParametroClausulaContratoDAO getParametroClausulaContratoDAO() { ... }
}
```

---

## Notas de dependencias (orden de resolución en FK)

1. `Pais`
2. `Estrato`, `TipoDocumento`, `TipoPropiedad`, `TipoParticipante`, `TipoAplicacion`, `AreaReferencia`, `Parametro`
3. `Departamento` → depende de Pais
4. `ClausulaContrato` → depende de AreaReferencia, TipoAplicacion
5. `Ciudad` → depende de Departamento
6. `Persona` → depende de TipoDocumento, Ciudad
7. `Propiedad` → depende de TipoPropiedad, Estrato, Ciudad
8. `Contrato` → depende de Propiedad
9. `ParticipanteContrato` → depende de Persona, TipoParticipante, Contrato
10. `ClausulaPorContrato` → depende de Contrato, ClausulaContrato
11. `ParametroClausulaContrato` → depende de Parametro, ClausulaPorContrato
