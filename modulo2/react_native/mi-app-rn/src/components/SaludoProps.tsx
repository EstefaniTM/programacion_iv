import React from "react";
import { View, Text } from "react-native";
import Saludo from "./Saludo";

type Props = {
  nombre: string;
};

export default function SaludoProps({ nombre }: Props) {
  return (
    <Text style={{ fontSize: 18, color: "#58a6ff" }}>
      ¡Hola {nombre}!
    </Text>
  );
}