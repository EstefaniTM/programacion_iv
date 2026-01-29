import { Pressable, StyleSheet, Text, View } from "react-native";
import type { NativeStackScreenProps } from "@react-navigation/native-stack";
import type { RootStackParamList } from "@/navigation/types";

type Props = NativeStackScreenProps<RootStackParamList, "Profile">;

export function ProfileScreen({ navigation, route }: Props) {
  const { userId, name, lastName } = route.params;

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Perfil</Text>
      <Text style={styles.label}>Nombre:</Text>
      <Text style={styles.value}>{name}</Text>

      <Text style={[styles.label, { marginTop: 12 }]}>Apellido:</Text>
      <Text style={styles.value}>{lastName}</Text>

      <Text style={[styles.label, { marginTop: 12 }]}>User ID:</Text>
      <Text style={styles.value}>{userId}</Text>

      <Pressable style={styles.btn} onPress={() => navigation.goBack()}>
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
  },
  title: {
    color: "#58a6ff",
    fontWeight: "900",
    fontSize: 20,
    marginBottom: 16,
  },
  label: {
    color: "#c9d1d9",
    opacity: 0.85,
    fontWeight: "800",
  },
  value: {
    marginTop: 4,
    color: "#c9d1d9",
    fontWeight: "900",
    fontSize: 16,
  },
  btn: {
    marginTop: 18,
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
    fontWeight: "900",
  },
});