export interface CapacitorVlcPlayerPlugin {
  stream(options: { link: string }): Promise<{ link: string }>;
}
