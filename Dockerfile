# Dockerfile para Render
FROM python:3.11-slim

# Instalar wkhtmltopdf y dependencias
RUN apt-get update && apt-get install -y wkhtmltopdf

# Instalar dependencias Python
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copiar el código de la aplicación
COPY . /app

# Exponer el puerto
EXPOSE 8080

# Comando para ejecutar la app
CMD ["python", "app.py"]
