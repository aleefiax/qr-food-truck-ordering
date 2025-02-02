import React, { useState } from 'react';
import axios from 'axios';

const QRCodeGenerator = () => {
  const [qrCode, setQrCode] = useState<string | null>(null); // State to hold the QR code image as a data URL

  const generateQRCode = async () => {
    const url = "https://yourfrontend.com/menu"; // The URL to encode in the QR code

    try {
      // Send the URL to the backend to generate the QR code
      const response = await axios.post('http://localhost:8080/api/qr/generate', url, {
        responseType: 'arraybuffer', // Important to get the image data as binary
      });

      // Convert the binary image data to a base64 string
      const base64Image = Buffer.from(response.data, 'binary').toString('base64');
      setQrCode(`data:image/png;base64,${base64Image}`); // Set the QR code image as a base64 string
    } catch (error) {
      console.error('Error generating QR code:', error);
    }
  };

  return (
    <div>
      <h1>Generate QR Code</h1>
      <button onClick={generateQRCode}>Generate QR Code</button>
      {qrCode && (
        <div>
          <h2>Scan this QR code:</h2>
          <img src={qrCode} alt="Generated QR Code" />
        </div>
      )}
    </div>
  );
};

export default QRCodeGenerator;
