import { Image } from 'expo-image';
import { Platform, StyleSheet } from 'react-native';

import { HelloWave } from '@/components/hello-wave';
import ParallaxScrollView from '@/components/parallax-scroll-view';
import { ThemedText } from '@/components/themed-text';
import { ThemedView } from '@/components/themed-view';
import { Link } from 'expo-router';

export default function HomeScreen() {
  return (
    <ParallaxScrollView
      headerBackgroundColor={{ light: '#A1CEDC', dark: '#1D3D47' }}
      headerImage={
        <Image
          source={require('@/assets/images/partial-react-logo.png')}
          style={styles.reactLogo}
        />
      }>
      <ThemedView style={styles.titleContainer}>
        <ThemedText type="title">Welcome!</ThemedText>
        import React from "react";
        import { StyleSheet, Text, View } from "react-native";
        import { useRouter } from "expo-router";
        import MenuButton from "../../src/components/MenuButton";

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
        <ThemedText type="subtitle">Step 3: Get a fresh start</ThemedText>
