import { StyleSheet, Text, View } from "react-native";
import { Card } from "../components/common/Card";
import { Badge } from "../components/common/Badge";
import { PrimaryButton } from "../components/common/PrimaryButton";
import { DemoScreen } from "./DemoScreen";


export function Parte05Lab() {
  return (
    <View style={styles.container}>
        <DemoScreen />
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