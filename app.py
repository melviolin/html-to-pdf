from flask import Flask, request, send_file
import pdfkit
import io

app = Flask(__name__)

@app.route("/generate", methods=["POST"])
def generate_pdf():
    # Obtener el contenido HTML enviado en la solicitud POST
    html_content = request.get_data(as_text=True)

    # Convertir el HTML a PDF usando pdfkit
    pdf_bytes = pdfkit.from_string(html_content, False)

    # Crear un archivo PDF en memoria
    pdf_output = io.BytesIO(pdf_bytes)

    # Enviar el PDF como respuesta con nombre de archivo
    return send_file(pdf_output, mimetype='application/pdf', as_attachment=True, download_name="documento.pdf")

if __name__ == "__main__":
    app.run(debug=True)
