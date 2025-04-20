FROM python:3.11-slim

# Instala dependencias necesarias para que WeasyPrint funcione correctamente
RUN apt-get update && apt-get install -y \
    build-essential \
    libpango-1.0-0 \
    libpangocairo-1.0-0 \
    libgdk-pixbuf2.0-0 \
    libcairo2 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copia los archivos del proyecto
COPY . .

# Instala dependencias de Python
RUN pip install --no-cache-dir -r requirements.txt

EXPOSE 8080

# Comando de arranque
CMD ["python", "app.py"]
