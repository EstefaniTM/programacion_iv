import { Pressable, StyleSheet, Text, View } from "react-native";
import type { NativeStackScreenProps } from "@react-navigation/native-stack";
import type { RootStackParamList } from "@/navigation/types";

type Props = NativeStackScreenProps<RootStackParamList, "GlobalCounter">;

export function GlobalCounterScreen({ navigation }: Props) {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Global Counter</Text>
      <Text style={styles.body}>Contador usando Context API</Text>

      <View style={styles.counterContainer}>
        <Text style={styles.counter}>0</Text>
      </View>

      <View style={styles.buttonRow}>
        <Pressable style={styles.btn} onPress={() => {}}>
          <Text style={styles.btnText}>- 1</Text>
        </Pressable>
        <Pressable style={styles.btn} onPress={() => {}}>
          <Text style={styles.btnText}>+ 1</Text>
        </Pressable>
      </View>

      <Pressable style={[styles.btn, { marginTop: 20 }]} onPress={() => navigation.goBack()}>
        <Text style={styles.btnText}>Volver</Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#0d1117",
    padding: 16,
    justifyContent: "center",
    alignItems: "center",
  },
  title: {
    color: "#58a6ff",
    fontWeight: "900",
    fontSize: 22,
    marginBottom: 6,
  },
  body: {
    color: "#c9d1d9",
    opacity: 0.9,
    lineHeight: 20,
    marginBottom: 20,
  },
  counterContainer: {
    backgroundColor: "#21262d",
    borderRadius: 12,
    padding: 20,
    marginBottom: 20,
  },
  counter: {
    color: "#58a6ff",
    fontSize: 48,
    fontWeight: "900",
  },
  buttonRow: {
    flexDirection: "row",
    gap: 12,
  },
  btn: {
    backgroundColor: "#21262d",
    borderColor: "#58a6ff",
    borderWidth: 1,
    borderRadius: 10,
    paddingVertical: 10,
    paddingHorizontal: 20,
    alignItems: "center",
  },
  btnText: {
    color: "#58a6ff",
    fontWeight: "800",
    fontSize: 18,
  },
});
