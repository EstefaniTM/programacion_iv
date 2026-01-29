import { StyleSheet, Text, View } from "react-native";

type Props = {
  danger?: boolean;
};

export function Banner({ danger = false }: Props) {
  return (
    <View style={[styles.base, danger ? styles.danger : styles.normal]}>
      <Text style={styles.text}>{danger ? "Alerta" : "Info"}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  base: {
    paddingVertical: 10,
    paddingHorizontal: 12,
    borderRadius: 10,
    borderWidth: 1,
    borderColor: "#30363d",
  },
  normal: {
    backgroundColor: "#161b22",
  },
  danger: {
    backgroundColor: "#2d0b0b",
    borderColor: "#f85149",
  },
  text: {
    color: "#c9d1d9",
    fontWeight: "700",
  },
});