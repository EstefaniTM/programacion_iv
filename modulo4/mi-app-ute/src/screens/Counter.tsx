import { useState } from "react";
import { Pressable, StyleSheet, Text, View } from "react-native";

export function Counter() {
  const [count, setCount] = useState<number>(0);

  return (
    <View style={styles.container}>
      <Text style={styles.value}>{count}</Text>

      <Pressable
        style={styles.btn}
        onPress={() => setCount(count + 1)}
      >
        <Text style={styles.btnText}>Sumar</Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: "#0d1117",
    padding: 16,
    alignItems: "center",
    justifyContent: "center",
    flex: 1,
  },
  value: {
    color: "#58a6ff",
    fontSize: 34,
    fontWeight: "900",
    marginBottom: 14,
  },
  btn: {
    backgroundColor: "#21262d",
    borderColor: "#58a6ff",
    borderWidth: 1,
    paddingVertical: 10,
    paddingHorizontal: 14,
    borderRadius: 10,
  },
  btnText: {
    color: "#58a6ff",
    fontWeight: "800",
  },
});
