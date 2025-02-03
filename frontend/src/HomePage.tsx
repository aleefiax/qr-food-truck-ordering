// src/HomePage.tsx
import React from "react";
import QRCodeGenerator from "./QRCodeGenerator"; // Import the QRCodeGenerator component

const HomePage = () => {
  return (
    <div style={{ textAlign: "center", padding: "20px" }}>
      <h1>Welcome to the Food Truck</h1>
      <p>Scan the QR code to place your order!</p>

      <QRCodeGenerator />
    </div>
  );
};

export default HomePage;
