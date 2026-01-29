import { useEffect, useState } from "react";
import { ActivityIndicator, StyleSheet, Text, View } from "react-native";

type ClienteDTO = { id: number; nombre: string };

export function AbortFetchDemo() {
  const [posts, setPosts] = useState<ClienteDTO[]>([]);
  const [loading, setLoading] = useState<boolean>(false);

  useEffect(() => {
    const controller = new AbortController();

    async function load() {
      setLoading(true);
      const res = await fetch("https://funeraria-api.desarrollo-software.xyz/api/clientes/?page=1&page_size=20", {
        signal: controller.signal,
      });
      const data = (await res.json()) as ClienteDTO[];
      setPosts(data);
      setLoading(false);
    }

    load().catch(() => {
      setLoading(false);
    });

    return () => {
      controller.abort();
    };
  }, []);

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Clientes</Text>

      {loading ? (
        <View style={styles.row}>
          <ActivityIndicator />
          <Text style={styles.body}>Cargando clientes...</Text>
        </View>
      ) : null}

      {!loading ? (
        <View style={styles.list}>
          {posts.map((p) => (
            <Text key={p.id} style={styles.item}>• {p.nombre}</Text>
          ))}
        </View>
      ) : null}
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
    color: "#58a6ff",
    fontWeight: "900",
    fontSize: 18,
    marginBottom: 10,
  },
  body: {
    color: "#c9d1d9",
    marginLeft: 8,
    opacity: 0.9,
  },
  row: {
    flexDirection: "row",
    alignItems: "center",
    marginBottom: 10,
  },
  list: {
    backgroundColor: "#161b22",
    borderColor: "#30363d",
    borderWidth: 1,
    borderRadius: 12,
    padding: 12,
  },
  item: {
    color: "#c9d1d9",
    opacity: 0.95,
    lineHeight: 20,
    marginBottom: 6,
  },
});