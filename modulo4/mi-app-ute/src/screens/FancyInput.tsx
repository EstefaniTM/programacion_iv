import { useEffect } from "react";
import { StyleSheet, Text, View } from "react-native";
import type { NativeStackScreenProps } from "@react-navigation/native-stack";
import type { RootStackParamList } from "@/navigation/types";

type Props = NativeStackScreenProps<RootStackParamList, "Details">;

export function DetailsScreen({ navigation, route }: Props) {
  const { id, title } = route.params;

  useEffect(() => {
    navigation.setOptions({ title });
  }, [navigation, title]);

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Details</Text>
      <Text style={styles.text}>Title: {title}</Text>
      <Text style={styles.text}>ID: {id}</Text>
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
    fontWeight: "900",
    fontSize: 20,
    marginBottom: 12,
  },
  text: {
    color: "#c9d1d9",
    fontWeight: "800",
    marginBottom: 8,
  },
});