# Imagen base oficial con soporte completo de wkhtmltopdf
FROM python:3.10-slim

# Instala dependencias necesarias para wkhtmltopdf con Qt
RUN apt-get update && apt-get install -y \
    wkhtmltopdf \
    libxrender1 \
    libxext6 \
    libfontconfig1 \
    xfonts-base \
    xfonts-75dpi \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Crea directorio de trabajo
WORKDIR /app

# Copia los archivos de tu proyecto
COPY . .

# Instala los paquetes de Python
RUN pip install --no-cache-dir -r requirements.txt

# Expone el puerto para Render
EXPOSE 8080

# Comando de inicio
CMD ["python", "app.py"]
