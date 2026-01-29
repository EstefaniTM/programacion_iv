import { useCallback, useMemo, useState } from "react";
import {
  FlatList,
  Pressable,
  RefreshControl,
  StyleSheet,
  Text,
  View,
} from "react-native";
import { PRODUCTS, type Product } from "@/data/products";
import type { NativeStackScreenProps } from "@react-navigation/native-stack";
import type { RootStackParamList } from "@/navigation/types";

type Props = NativeStackScreenProps<RootStackParamList, "Products">;

export function ProductsListScreen({ navigation }: Props) {
  const [refreshing, setRefreshing] = useState<boolean>(false);
  const [minPrice, setMinPrice] = useState<number>(0);

  const bg = "#0d1117";
  const card = "#161b22";
  const border = "#30363d";
  const text = "#c9d1d9";
  const primary = "#58a6ff";

  const data = useMemo(() => {
    // filtro simple para ver que la lista reacciona
    return PRODUCTS.filter((p) => p.price >= minPrice);
  }, [minPrice]);

  const onRefresh = useCallback(() => {
    setRefreshing(true);
    // simulación de refresh
    setTimeout(() => {
      setMinPrice((v) => (v === 0 ? 20 : 0));
      setRefreshing(false);
    }, 700);
  }, []);

  const renderItem = useCallback(({ item }: { item: Product }) => {
    return (
      <Pressable
        style={[styles.row, { backgroundColor: card, borderColor: border }]}
        onPress={() =>
          navigation.navigate("Details", { id: item.id, title: item.name, detail: item.description })
        }
      >
        <View>
          <Text style={[styles.name, { color: text }]}>{item.name}</Text>
          <Text style={[styles.sub, { color: text }]}>ID: {item.id}</Text>
        </View>

        <Text style={[styles.price, { color: primary }]}>${item.price}</Text>
      </Pressable>
    );
  }, [navigation]);

  const keyExtractor = useCallback((item: Product) => item.id, []);

  const ItemSeparator = useCallback(() => {
    return <View style={{ height: 10 }} />;
  }, []);

  const ListEmpty = useCallback(() => {
    return (
      <View style={styles.empty}>
        <Text style={{ color: text, fontWeight: "800" }}>No hay productos</Text>
        <Text style={{ color: text, opacity: 0.8, marginTop: 6 }}>
          Tip: cambia el filtro (minPrice) para ver la lista vacía.
        </Text>
      </View>
    );
  }, []);

  return (
    <View style={[styles.container, { backgroundColor: bg }]}>
      <View style={styles.header}>
        <Text style={[styles.title, { color: primary }]}>Productos</Text>

        <Pressable
          style={[styles.filterBtn, { borderColor: primary }]}
          onPress={() => setMinPrice((v) => (v === 0 ? 999 : 0))}
        >
          <Text style={[styles.filterText, { color: primary }]}>
            {minPrice === 0 ? "Vaciar lista" : "Reset filtro"}
          </Text>
        </Pressable>
      </View>

      <FlatList
        data={data}
        keyExtractor={keyExtractor}
        renderItem={renderItem}
        ItemSeparatorComponent={ItemSeparator}
        contentContainerStyle={{ paddingBottom: 20 }}
        ListEmptyComponent={ListEmpty}
        refreshControl={
          <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
        }
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
  },
  header: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    marginBottom: 12,
  },
  title: {
    fontWeight: "900",
    fontSize: 18,
  },
  filterBtn: {
    backgroundColor: "#21262d",
    borderWidth: 1,
    borderRadius: 10,
    paddingVertical: 8,
    paddingHorizontal: 12,
  },
  filterText: {
    fontWeight: "900",
  },
  row: {
    borderWidth: 1,
    borderRadius: 12,
    padding: 12,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  name: {
    fontWeight: "900",
    fontSize: 15,
  },
  sub: {
    marginTop: 4,
    opacity: 0.8,
  },
  price: {
    fontWeight: "900",
    fontSize: 16,
  },
  empty: {
    padding: 16,
    borderRadius: 12,
    borderWidth: 1,
    borderColor: "#30363d",
    backgroundColor: "#161b22",
  },
});