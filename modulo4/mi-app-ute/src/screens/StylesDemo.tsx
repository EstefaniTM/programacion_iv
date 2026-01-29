import { StyleSheet, Text, View } from "react-native";

export function StylesDemo() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>StyleSheet en React Native</Text>
      <Text style={styles.body}>Estilos como objetos, sin CSS tradicional.</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#0d1117",
    padding: 16,
    justifyContent: "center",
  },
  title: {
    color: "#58a6ff",
    fontSize: 18,
    fontWeight: "800",
    marginBottom: 6,
  },
  body: {
    color: "#c9d1d9",
    opacity: 0.9,
    lineHeight: 20,
  },
});