import React from "react";
import { StyleSheet, Text, View } from "react-native";
import { useRouter } from "expo-router";
import MenuButton from "../src/components/MenuButton";

export default function MenuScreen() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text style={styles.title}>RN Mini Dashboard</Text>
      <Text style={styles.subtitle}>Menú principal (Expo Router)</Text>

      <View style={{ gap: 12, marginTop: 14 }}>
        <MenuButton
          title="1) Products (API)"
          subtitle="Lista paginada desde /products/"
          onPress={() => router.push("/products")}
        />

        <MenuButton
          title="2) Math 1 — Área Trapecio"
          subtitle="A = ((B + b) / 2) * h"
          onPress={() => router.push("/math-trapecio")}
        />

        <MenuButton
          title="3) Math 2 — Total con IVA y Descuento"
          subtitle="subtotal + IVA - descuento"
          onPress={() => router.push("/math-total")}
        />
      </View>

      <Text style={styles.note}>
        Tip: Cada pantalla practica estado, inputs y cálculos simples.
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 16, backgroundColor: "#f6f7fb" },
  title: { fontSize: 24, fontWeight: "900" },
  subtitle: { marginTop: 4, color: "#555", fontWeight: "700" },
  note: { marginTop: 18, color: "#1976d2", fontWeight: "800" },
});
