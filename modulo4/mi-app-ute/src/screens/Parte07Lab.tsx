import { StyleSheet, Text, View } from "react-native";
import { Counter } from "./Counter";
import { LoginFormDemo } from "./LoginFormDemo";
import { promedio_notas as PromedioNotas } from "./promedio_notas";
export function Parte07Lab() {
  return (
    <View style={styles.stage}>
      <PromedioNotas />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#0d1117",
    padding: 16,
  },
  h1: {
    color: "#58a6ff",
    fontWeight: "900",
    fontSize: 18,
    marginBottom: 12,
  },
  stage: {
    flex: 1,
    borderRadius: 12,
    overflow: "hidden",
    borderWidth: 1,
    borderColor: "#30363d",
  },
});