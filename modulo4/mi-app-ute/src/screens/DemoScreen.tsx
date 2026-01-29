import { useState } from "react";
import { StyleSheet, Text, View } from "react-native";
import { Card } from "../components/common/Card";
import { Badge } from "../components/common/Badge";
import { PrimaryButton } from "../components/common/PrimaryButton";

export function DemoScreen() {
  const [count, setCount] = useState<number>(0);

  const variant = count >= 5 ? "SUCCESS" : "INFO";

  return (
    <View style={styles.container}>
      <Card>
        <Badge label={`Clicks: ${count}`} variant={variant} />
        <Text style={styles.title}>Componentes reutilizables</Text>
        <Text style={styles.body}>Cada pieza tiene props tipadas y se compone.</Text>
        <View style={styles.actions}>
          <PrimaryButton title="Sumar" onPress={() => setCount(count + 1)} />
        </View>
      </Card>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#0d1117",
    justifyContent: "center",
    padding: 16,
  },
  title: {
    marginTop: 10,
    color: "#58a6ff",
    fontWeight: "800",
    fontSize: 16,
  },
  body: {
    marginTop: 6,
    color: "#c9d1d9",
    opacity: 0.9,
    lineHeight: 20,
  },
  actions: {
    marginTop: 12,
  },
});