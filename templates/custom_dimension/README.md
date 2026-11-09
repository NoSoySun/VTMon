# Plantilla de dimensión personalizada (Minecraft 1.21.1)

Esta carpeta contiene un ejemplo mínimo de datapack para añadir una dimensión basada en la generación del Overworld. No se carga desde el mod mientras permanezca en `templates/`.

## Cómo usarla

1. Copia la carpeta `data` a la raíz de tu datapack. Para probarla en un mundo, por ejemplo, crea `saves/<mundo>/datapacks/mi_dimension/data/` y copia ahí `data/`.
2. Cambia `example_dimension` en las rutas y referencias por un identificador en minúsculas (`a-z`, `0-9`, `_`, `-`):
   - `data/example_dimension/dimension/mi_dimension.json`
   - `data/example_dimension/dimension_type/mi_dimension.json`
   - El valor `type` del archivo de dimensión debe coincidir con el identificador del `dimension_type`.
3. Edita las propiedades de `dimension_type/mi_dimension.json` para configurar altura, ciclo día/noche, clima y reglas como camas o anclas.
4. En `dimension/mi_dimension.json`, cambia `settings` y `biome_source` si quieres otra generación. El ejemplo usa los biomas y el terreno del Overworld.
5. Entra al mundo y ejecuta `/reload`. Puedes probar el acceso con `/execute in example_dimension:mi_dimension run tp @s 0 100 0`.

Para personalizar biomas o terreno, añade los recursos correspondientes del datapack en `data/<namespace>/worldgen/biome/`, `worldgen/noise_settings/` y/o `worldgen/configured_feature/`. La generación de terreno avanzada necesita referencias válidas a esos recursos; consulta un datapack compatible con tu versión de Minecraft como modelo.

## Archivos

- `data/example_dimension/dimension/mi_dimension.json`: define el generador y el tipo usados por la dimensión.
- `data/example_dimension/dimension_type/mi_dimension.json`: define las reglas físicas y ambientales.

El namespace `example_dimension` y `mi_dimension` son nombres de ejemplo: reemplázalos antes de distribuir el datapack para evitar conflictos.
