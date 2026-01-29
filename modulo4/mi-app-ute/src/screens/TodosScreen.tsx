import { FlatList, Pressable, StyleSheet, Text, View } from "react-native";
import { useTodos } from "../hooks/useTodos";

export function TodosScreen() {
  const { state, reload } = useTodos(8);

  if (state.status === "LOADING" || state.status === "IDLE") {
    return (
      <View style={styles.center}>
        <Text style={styles.text}>Cargando...</Text>
      </View>
    );
  }

  if (state.status === "ERROR") {
    return (
      <View style={styles.center}>
        <Text style={styles.error}>{state.error}</Text>
        <Pressable style={styles.btn} onPress={reload}>
          <Text style={styles.btnText}>Reintentar</Text>
        </Pressable>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Todos</Text>

      <FlatList
        data={state.data}
        keyExtractor={(item) => String(item.id)}
        renderItem={({ item }) => (
          <View style={styles.row}>
            <Text style={styles.rowText}>• {item.title}</Text>
          </View>
        )}
      />

      <Pressable style={styles.btn} onPress={reload}>
        <Text style={styles.btnText}>Recargar</Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#0d1117",
    padding: 16,
  },
  title: {
    color: "#58a6ff",
    fontWeight: "900",
    fontSize: 18,
    marginBottom: 10,
  },
  row: {
    paddingVertical: 10,
    borderBottomColor: "#30363d",
    borderBottomWidth: 1,
  },
  rowText: {
    color: "#c9d1d9",
    opacity: 0.9,
  },
  center: {
    flex: 1,
    backgroundColor: "#0d1117",
    alignItems: "center",
    justifyContent: "center",
    padding: 16,
  },
  text: {
    color: "#c9d1d9",
    fontWeight: "700",
  },
  error: {
    color: "#f85149",
    fontWeight: "800",
    marginBottom: 10,
  },
  btn: {
    marginTop: 12,
    backgroundColor: "#21262d",
    borderColor: "#58a6ff",
    borderWidth: 1,
    borderRadius: 10,
    paddingVertical: 10,
    paddingHorizontal: 14,
    alignItems: "center",
  },
  btnText: {
    color: "#58a6ff",
    fontWeight: "800",
  },
});