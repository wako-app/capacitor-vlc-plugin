export interface VlcPlayerPlugin {
  stream(options: { link: string }): Promise<{ link: string }>;
}
