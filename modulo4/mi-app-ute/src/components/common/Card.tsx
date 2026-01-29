import type { ReactNode } from "react";
import { StyleSheet, View } from "react-native";

type CardProps = {
  children: ReactNode;
};

export function Card({ children }: CardProps) {
  return <View style={styles.card}>{children}</View>;
}

const styles = StyleSheet.create({
  card: {
    backgroundColor: "#161b22",
    borderColor: "#30363d",
    borderWidth: 1,
    padding: 14,
    borderRadius: 12,
  },
});