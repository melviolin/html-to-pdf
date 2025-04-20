from flask import Flask, request, send_file
from reportlab.lib.pagesizes import letter
from reportlab.pdfgen import canvas
import io

app = Flask(__name__)

@app.route("/generate", methods=["POST"])
def generate_pdf():
    # Obtener el contenido HTML (solo como ejemplo, ya que ReportLab no usa HTML)
    html_content = request.get_data(as_text=True)

    # Crear un objeto BytesIO para almacenar el PDF
    pdf_output = io.BytesIO()

    # Crear un canvas (PDF) con ReportLab
    c = canvas.Canvas(pdf_output, pagesize=letter)

    # Escribir contenido en el PDF (esto es solo un ejemplo)
    c.drawString(100, 750, "Contenido generado desde ReportLab")
    c.drawString(100, 730, f"HTML recibido: {html_content[:50]}")  # Ejemplo de contenido HTML

    # Finalizar el PDF
    c.save()

    # Rewind al inicio del archivo en memoria
    pdf_output.seek(0)

    # Enviar el PDF como respuesta
    return send_file(pdf_output, mimetype='application/pdf', as_attachment=True, download_name="documento.pdf")

if __name__ == "__main__":
    app.run(debug=True)
