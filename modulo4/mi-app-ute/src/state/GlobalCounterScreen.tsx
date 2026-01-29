import { Pressable, StyleSheet, Text, View } from "react-native";
import { useAppDispatch, useAppState } from "@/state/app.hooks";

export function GlobalCounterScreen() {
  const { counter } = useAppState();
  const dispatch = useAppDispatch();

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Counter Global</Text>
      <Text style={styles.value}>{counter}</Text>

      <View style={styles.row}>
        <Pressable style={styles.btn} onPress={() => dispatch({ type: "COUNTER/DEC" })}>
          <Text style={styles.btnText}>-1</Text>
        </Pressable>

        <Pressable style={styles.btn} onPress={() => dispatch({ type: "COUNTER/INC" })}>
          <Text style={styles.btnText}>+1</Text>
        </Pressable>
      </View>

      <Pressable style={[styles.btn, { marginTop: 12 }]} onPress={() => dispatch({ type: "COUNTER/RESET" })}>
        <Text style={styles.btnText}>Reset</Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#0d1117",
    justifyContent: "center",
    alignItems: "center",
    padding: 16,
  },
  title: {
    color: "#58a6ff",
    fontWeight: "900",
    fontSize: 18,
    marginBottom: 10,
  },
  value: {
    color: "#c9d1d9",
    fontWeight: "900",
    fontSize: 44,
    marginBottom: 14,
  },
  row: {
    flexDirection: "row",
    gap: 12,
  },
  btn: {
    backgroundColor: "#21262d",
    borderColor: "#58a6ff",
    borderWidth: 1,
    borderRadius: 10,
    paddingVertical: 10,
    paddingHorizontal: 16,
  },
  btnText: {
    color: "#58a6ff",
    fontWeight: "900",
  },
});