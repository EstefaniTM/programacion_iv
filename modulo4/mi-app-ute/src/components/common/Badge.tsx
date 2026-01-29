import { StyleSheet, Text, View } from "react-native";

type BadgeVariant = "INFO" | "SUCCESS" | "WARNING" | "DANGER";

type BadgeProps = {
  label: string;
  variant?: BadgeVariant;
};

export function Badge({ label, variant = "INFO" }: BadgeProps) {
  const bg =
    variant === "SUCCESS" ? "#1f6feb" :
    variant === "WARNING" ? "#d29922" :
    variant === "INFO" ? "#238636" :
    variant === "DANGER" ? "#f85149" :
    "#30363d";

  return (
    <View style={[styles.badge, { backgroundColor: bg }]}>
      <Text style={styles.text}>{label}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  badge: {
    paddingVertical: 6,
    paddingHorizontal: 10,
    borderRadius: 999,
    alignSelf: "flex-start",
  },
  text: {
    color: "#0d1117",
    fontWeight: "700",
    fontSize: 12,
  },
});